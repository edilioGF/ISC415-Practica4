<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand nav-title" href="/">spark</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
            </li>
            <#if currentUser??>
                <#if currentUser.author>
                    <li class="nav-item">
                        <a class="nav-link" href="/tags">Tags</a>
                    </li>
                </#if>
                <#if currentUser.admin>
                    <li class="nav-item">
                        <a class="nav-link" href="/users/register">Register user</a>
                    </li>
                </#if>
            </#if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <#if currentUser??>
            <li class="nav-item">
                <a class="nav-link" href="/profile/${currentUser.username}">My profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout">Logout</a>
            </li>
            <#else>
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            </#if>
        </ul>
    </div>
</nav>