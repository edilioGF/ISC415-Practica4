<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Reset password</title>
        <#include "partials/header_info.ftl" >
    </head>
    <body>
        <#include "partials/messages.ftl">
        <div class="container-fluid">
            <div class="content-wrapper d-flex align-items-start px-0">
                <div class="row w-100 mx-0">
                    <div class="col-lg-8 mx-auto">
                        <div class="text-left py-5 px-4 px-sm-5">
                            <h2>Reset password</h2>
                            <form class="pt-5 ml-5" method="POST" action="/resetpassword">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="username" type="text" class="form-control" placeholder="Username" maxlength="32" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="password" type="password" class="form-control" placeholder="New password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="confirm-password" type="password" class="form-control" placeholder="Confirm new password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-3">
                                    <button type="submit" class="btn btn-primary px-4 py-2">Reset password</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "partials/general_scripts.ftl" >
    </body>
</html>