package com.blogspot.atomskjournal.controller;

import com.blogspot.atomskjournal.Services.DataService;
import com.blogspot.atomskjournal.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HomeController {

	@Autowired
	DataService dataService;

    // Создание юзера
	@RequestMapping("register")
	public String registerUser(@Validated @ModelAttribute("user") User user, BindingResult result, RedirectAttributes attributes) {

        // Валидация введенных в поля данных
        FormValidator formValidator = new FormValidator();
        formValidator.validate(user, result);

        if (result.hasErrors()) {
            // Пробрасываем ошибки дальше в главное окно, оно покажет тексты ошибок рядом с полями

            attributes.addFlashAttribute("bindingResult", result);
            attributes.addFlashAttribute("user", user);

            return "redirect:list";
        }

        // Все ок, юзер создан
        dataService.insertRow(user);
		return "created";
	}


    // Поиск
    @RequestMapping("search")
    public ModelAndView search(@RequestParam String name) {

        ModelAndView mav = new ModelAndView("redirect:list");
        mav.addObject("user", new User());

        // Костыль. На случай если в поле "поиск" введено слишком много текста.
        // В случае с поиском не получается поступать аналогично с другими полями ввода,
        // поскольку в list.jsp path у input search такой же, как и у input name, и ошибку
        // показывает и там, и там.

        if (name.length() < 30) {
            mav.addObject("name", name);
            return mav;
        }

        // Пробрасываем модель с поисковым ключом в главное окно
        return mav;


    }

    // Главное окно
	@RequestMapping(value = "list", method = RequestMethod.GET)

    public String userList(@ModelAttribute("user") User user, Model model, HttpServletRequest request, HttpServletResponse response) {



        // Достаем ключ для поиска
        String keyword = request.getParameter("name");


        List<User> listUsers;
        PagedListHolder<User> pagedListHolder;

        if (keyword == null || keyword.equals("")) {
            listUsers = dataService.userList();

        } else {
            listUsers = dataService.searchForName(keyword);
        }


        pagedListHolder = new PagedListHolder(listUsers);

        int pageSize = 10;
        pagedListHolder.setPageSize(pageSize);
        int page = ServletRequestUtils.getIntParameter(request, "p", 0);
        pagedListHolder.setPage(page);


        // Если прилетели ошибки из форм
        if (model.asMap().containsKey("bindingResult")) {

            model.addAttribute("org.springframework.validation.BindingResult.user",
                    model.asMap().get("bindingResult"));

        }

        // Если модель прилетела с уже сформированным юзером, не нужно создавать нового
        if (!model.asMap().containsKey("user")) {

            model.addAttribute("user", new User());
        }



        model.addAttribute("pagedListHolder", pagedListHolder);
        model.addAttribute("userList", pagedListHolder.getPageList());

        // Обновляем
        return "list";
	}


    // Удаление
	@RequestMapping("delete")
	public ModelAndView deleteUser(@RequestParam int id) {
		dataService.deleteRow(id);
		return new ModelAndView("deleted");
	}

    // Редирект к редактированию
	@RequestMapping("edit")
	public ModelAndView editUser(@RequestParam int id,
								 @ModelAttribute User user) {
		User currentUser = dataService.getRowById(id);
		return new ModelAndView("edit", "userObject", currentUser);
	}

    // Обновление поля после редактирования
	@RequestMapping("update")
	public String updateUser(@ModelAttribute User user, BindingResult result, RedirectAttributes attributes ) {
        // Валидация введенных в поля данных
        FormValidator formValidator = new FormValidator();
        formValidator.validate(user, result);

        if (result.hasErrors()) {
            // Пробрасываем ошибки обратно в окно редактировния, оно покажет тексты ошибок рядом с полями

            attributes.addFlashAttribute("bindingResult", result);
            attributes.addFlashAttribute("user", user);

            return "edit";
        }
		dataService.updateRow(user);
		return "updated";
	}
}
