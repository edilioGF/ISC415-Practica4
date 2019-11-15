<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Profile - ${user.username}</title>
        <#include "partials/header_info.ftl" >
    </head>

    <body>
        <#include "partials/navbar.ftl" >
        <#include "partials/messages.ftl" >
        <div class="container">
            <div class="row profile">
                <div class="col-md-3">
                    <div class="profile-sidebar">
                        <div class="profile-usertitle">
                            <div class="profile-usertitle-name">
                                ${user.name}
                            </div>
                            <div class="profile-usertitle-username">
                                ${user.username}
                            </div>
                            <#if user.admin>
                                <div class="profile-usertitle-job">
                                    Admin
                                </div>
                            </#if>
                            <#if user.author>
                                <div class="profile-usertitle-job">
                                    Author
                                </div>
                            </#if>
                        </div>
                        <#if currentUser?? && currentUser.id == user.id>
                            <div class="profile-userbuttons mt-4">
                                <a href="/profile/edit/${user.username}">
                                    <button type="submit" class="btn btn-info btn-sm">Edit profile</button>
                                </a>
                            </div>
                            <div class="profile-userbuttons">
                                <button type="button" class="btn btn-danger btn-sm">Delete profile</button>
                            </div>
                        </#if>
                    </div>
                </div>
                <div class="col-md-9 v-divider">
                    <div class="profile-content">
                    <#if user.author>
                    <h2>
                        Published articles
                        <#if currentUser?? && currentUser.id == user.id>
                        <a class="btn btn-primary float-right" href="/article/write">
                            <i class="ti-plus text-white mr-2"></i>
                            Write article
                        </a>
                        </#if>
                    </h2>
                    <hr>
                    <#list articles as article>
                        <div class="panel">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-sm-9">
                                        <h4 class="pull-left">${article.title}</h4>
                                    </div>
                                    <div class="col-sm-3">
                                        <h5 class="pull-right">
                                            <small><em>${article.date}</em></small>
                                        </h5>
                                    </div>
                                </div>
                            </div>
                            <div class="panel-body article-body">
                                ${article.body?substring(0,200)}...
                                <br>
                                <a href="/article/view/${article.id}">Read more</a>
                            </div>
                        </div>
                        <hr>
                    </#list>
                    </#if>
                    </div>
                </div>
            </div>
        </div>
        <#include "partials/general_scripts.ftl" >
    </body>
</html>
