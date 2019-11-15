<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Tags</title>
        <#include "partials/header_info.ftl" >
    </head>

    <body>
        <#include "partials/navbar.ftl" >
        <#include "partials/messages.ftl" >
        <div class="container-fluid">
            <div class="content-wrapper d-flex align-items-start px-0">
                <div class="row w-100 mx-0">
                    <div class="col-lg-8 mx-auto">
                        <div class="text-left py-5 px-4 px-sm-5">
                            <#if currentUser?? && currentUser.author>
                            <form method="POST" action="/tags/register">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <label>Add new tag</label>
                                        <div class="input-group">
                                            <input name="tag" type="text" class="form-control" placeholder="Tag name" maxlength="32">
                                            <div class="input-group-append">
                                                <button class="btn btn-sm btn-primary" type="button">Register</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            </#if>
                            <div class="container">
                                <#list tags as tag>
                                    <div class="btn btn-sm btn-primary mb-2">
                                        <p class="d-inline-block m-0">${tag.name}</p>
                                        <a href="/tags/delete/${tag.id}">
                                            <small><i class="ti-close text-white"></i></small>
                                        </a>
                                    </div>
                                </#list>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "partials/general_scripts.ftl" >
    </body>
</html>
