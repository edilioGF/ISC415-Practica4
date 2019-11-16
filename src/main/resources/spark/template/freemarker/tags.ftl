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
                                                <button type="submit" class="btn btn-sm btn-primary">Register</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            </#if>
                            <div class="container">
                                <#list tags as tag>
                                    <div class="btn btn-sm btn-primary mb-2">
                                        <a href="/tags/${tag.id}" style="color:white;">
                                            <p class="d-inline-block m-0">${tag.name}</p>
                                        </a>
                                        <a href="/tags/delete/${tag.id}">
                                            <small><i class="ti-close text-white"></i></small>
                                        </a>
                                    </div>
                                </#list>
                            </div>
                        </div>
                        <#list articles as article>
                            <div class="panel">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-sm-9">
                                            <h3>${article.title}</h3>
                                        </div>
                                        <div class="col-sm-3">
                                            <h5><small>${article.date}</small></h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body article-body mb-2">
                                    ${article.body?substring(0,300)}...
                                    <a href="/article/view/${article.id}">Read more</a>
                                </div>
                                <#list article.tags as tag>
                                    <div class="btn btn-sm btn-primary py-0 mb-2">
                                        <p class="d-inline-block m-0">${tag.name}</p>
                                    </div>
                                    <#if tag?index == 4>
                                        <#break>
                                    </#if>
                                </#list>
                            </div>
                            <hr>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <#include "partials/general_scripts.ftl" >
    </body>
</html>
