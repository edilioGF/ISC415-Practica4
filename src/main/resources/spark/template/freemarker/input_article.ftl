<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${action} article</title>
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
                            <h2>${action} article</h2>
                            <#if action == "Write">
                            <form class="pt-5 ml-5" method="POST" action="/article/write">
                            <#elseif action == "Edit">
                            <form class="pt-5 ml-5" method="POST" action="/article/edit/${article.id}">
                            </#if>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <label for="title">Title</label>
                                                <input id="title" name="title" type="text" class="form-control" placeholder="Title" maxlength="96" value="${article.title}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-sm-12 p-0">
                                                <label for="body">Body</label>
                                                <textarea id="body" name="body" class="form-control resize-none" rows="14" minlength="300" placeholder="Article body">${article.body}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr>
                                <div class="container">
                                    <h4 class="mb-3">Select tags</h4>
                                    <#list tags as tag>
                                        <div class="btn btn-sm btn-outline-primary mb-2 c-pointer" >
                                            <p class="d-inline-block m-0" id="${tag.id}">${tag.name}</p>
                                        </div>
                                    </#list>
                                </div>
                                <div id="tag-holder" class="d-none"></div>
                                <div class="mt-3">
                                    <button type="submit" class="btn btn-primary px-4 py-2">Post</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <#include "partials/general_scripts.ftl" >
        <script src="/js/tag_selector.js"></script>
    </body>
</html>