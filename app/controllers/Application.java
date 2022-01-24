package controllers;

import models.Login;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.*;
import views.html.*;
import static play.data.Form.form;

public class Application extends Controller {
    @AddCSRFToken
    public static Result login() {
        return ok(login.render(form(Login.class)));
    }

    @RequireCSRFCheck
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()){
            return badRequest(views.html.login.render(loginForm));
        } else {
            session("username", loginForm.get().getUsername());
            String returnUrl = ctx().session.get("returnUrl");
            if(returnUrl == null || returnUrl.equals("") || returnUrl.equals(routes.Application.login().absoluteURL(request()))){
                returnUrl = routes.Application.index().url();
            }
            return redirect(returnUrl);
        }
    }

    @Security.Authenticated(models.Secured.class)
    public static Result logout() {
        session().clear();
        return redirect(routes.Application.login());
    }

    @Security.Authenticated(models.Secured.class)
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
}
