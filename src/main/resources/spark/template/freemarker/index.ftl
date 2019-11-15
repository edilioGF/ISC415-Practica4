<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
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
                            <a class="btn btn-primary" href="/article/write">
                                <i class="ti-plus text-white mr-2"></i>
                                Write article
                            </a>
                            <hr>
                            </#if>
                            <span class="mb-5"></span>
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
                                    ${article.body?substring(0,150)}...
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
        </div>
        <#include "partials/general_scripts.ftl" >
    </body>
</html>
