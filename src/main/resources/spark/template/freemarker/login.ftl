<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <#include "partials/header_info.ftl" >
    </head>

    <body>
        <#include "partials/messages.ftl" >
        <div class="container-fluid">
            <div class="content-wrapper d-flex align-items-center px-0">
                <div class="row w-100 mx-0">
                    <div class="col-lg-4 mx-auto">
                        <div class="text-left py-3 px-4 px-sm-5">
                            <div class="text-center">
                                <img src="/images/logo.svg" alt="logo">
                            </div>
                            <form class="pt-3" method="POST" action="/login">
                                <div class="form-group">
                                    <input name="username" type="text" class="form-control" placeholder="Username" maxlength="32">
                                </div>
                                <div class="form-group">
                                    <input name="password" type="password" class="form-control" placeholder="Password" maxlength="32">
                                </div>
                                <div class="custom-control custom-checkbox mt-2">
                                    <input name="remember" id="remember" type="checkbox" class="custom-control-input" />
                                    <label class="custom-control-label" for="remember">Remember me</label>
                                </div>
                                <div class="mt-3">
                                    <button id="login-btn" type="submit" class="btn btn-primary btn-block">Log in</button>
                                </div>
                            </form>
                            <div class="my-2 d-flex justify-content-between align-items-center">
                                <a href="/resetpassword">Forgot your password?</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "partials/general_scripts.ftl" >
    </body>
</html>
