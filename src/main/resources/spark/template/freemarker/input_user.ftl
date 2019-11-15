<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${action}</title>
        <#include "partials/header_info.ftl" >
    </head>
    <body>
        <#include "partials/navbar.ftl">
        <#include "partials/messages.ftl">
        <div class="container-fluid">
            <div class="content-wrapper d-flex align-items-start px-0">
                <div class="row w-100 mx-0">
                    <div class="col-lg-8 mx-auto">
                        <div class="text-left py-5 px-4 px-sm-5">
                            <h2>${action}</h2>
                            <#if action == "Register user">
                            <form class="pt-5 ml-5" method="POST" action="/users/register">
                            <#elseif action == "Edit profile">
                            <form class="pt-5 ml-5" method="POST" action="/profile/edit/${user.username}">
                            </#if>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="username" type="text" class="form-control" placeholder="Username" maxlength="32" value="${user.username}" <#if currentUser?? && !currentUser.admin>disabled</#if>/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="name" type="text" class="form-control" placeholder="Name" maxlength="32" value="${user.name}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <#if action == "Register user">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="password" type="password" class="form-control" placeholder="Password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <input name="confirm-password" type="password" class="form-control" placeholder="Confirm password" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="custom-control custom-checkbox">
                                            <input name="admin" id="admin" type="checkbox" class="custom-control-input" />
                                            <label class="custom-control-label" for="admin">Admin</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-12">
                                        <div class="custom-control custom-checkbox">
                                            <input name="author" id="author" type="checkbox" class="custom-control-input" />
                                            <label class="custom-control-label" for="author">Author</label>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                </#if>
                                <div class="mt-3">
                                    <button type="submit" class="btn btn-primary px-4 py-2">${action}</button>
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