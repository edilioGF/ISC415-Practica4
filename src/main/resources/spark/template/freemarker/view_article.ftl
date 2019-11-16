<!DOCTYPE html>
<html lang="en">
    <head>
        <title>${article.title}</title>
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
                            <h1 class="mt-4">${article.title}</h1>
                            <p class="lead">
                                by
                                <a href="/profile/${article.author.username}">${article.author.name}</a>
                                on ${article.date}
                                <#if currentUser?? && ((currentUser.id == article.author.id) || currentUser.admin)>
                                    <a class="btn btn-danger float-right" href="/article/delete/${article.id}">
                                        <i class="ti-trash text-white mr-2"></i>
                                        Delete
                                    </a>
                                    <a class="btn btn-primary float-right mr-2" href="/article/edit/${article.id}">
                                        <i class="ti-pencil text-white mr-2"></i>
                                        Edit article
                                    </a>
                                </#if>
                            </p>
                            <hr>
                            <p class="article-body">${article.body?replace("(\r\n)+", "<br><br>",'r')}</p>
                            <hr>
                            <#if currentUser??>
                                <div class="card my-4">
                                    <h5 class="card-header">Valoraciones</h5>
                                    <div class="card-body">
                                        <form action="/articles/${article.id}/like" method="post" style="display: inline-block;">
                                            <#if like == "null" || like == "false" >
                                                <button class="like color-grey" name="like" type="submit">
                                                    <span class="fa fa-thumbs-o-up color-grey" aria-hidden="true">&#128077;</span>
                                                </button>
                                            <#else>
                                                <button class="like" name="like" type="submit">
                                                    <span class="fa fa-thumbs-o-up" aria-hidden="true">&#128077;</span>
                                                </button>
                                            </#if>
                                        </form>
                                        <form action="/articles/${article.id}/dislike" method="post"  style="display: inline-block;">
                                            <#if like == "null" || like == "true" >
                                                <button class="dislike color-grey" name="dislike" type="submit">
                                                    <span class="fa fa-thumbs-o-down color-grey" aria-hidden="true">&#128078;</span>
                                                </button>
                                            <#else>
                                                <button class="dislike" name="dislike" type="submit">
                                                    <span class="fa fa-thumbs-o-down" aria-hidden="true">&#128078;</span>
                                                </button>
                                            </#if>
                                        </form>
                                        </br>
                                        <span class="likes-total"><b>${likesTotal}</b> Likes</span>
                                        <span class="likes-total"><b>${dislikesTotal}</b> Dislikes</span>
                                    </div>
                                </div>
                            </#if>
                            <hr>
                            <div class="card my-4">
                                <h5 class="card-header">Tags</h5>
                                <div class="container mt-2">
                                    <#list article.tags as tag>
                                        <div class="btn btn-sm btn-primary mb-2">
                                            <p class="d-inline-block m-0">${tag.name}</p>
                                        </div>
                                    </#list>
                                </div>
                            </div>
                            <hr>
                            <div class="card my-4">
                                <h5 class="card-header">Leave a Comment</h5>
                                <div class="card-body">
                                    <form method="POST" action="/article/comment/${article.id}">
                                        <div class="form-group">
                                            <textarea name="comment" class="form-control resize-none" rows="3" maxlength="200" <#if !currentUser??>disabled</#if>><#if !currentUser??>You need to login to comment on an article</#if></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary" <#if !currentUser??>disabled</#if>>Submit</button>
                                    </form>
                                </div>
                            </div>
                            <#list article.comments as comment>
                                <div class="media mb-4 mx-3">
                                    <div class="media-body">
                                        <h6 class="mt-0"><a href="/profile/${comment.author.username}">@${comment.author.username}</a></h6>
                                        ${comment.comment}
                                    </div>
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
