<#import "parts/common.ftlh" as c>

<@c.page>
<div class="row g-3">
    <div class="col-md-6">
        <form method="get" action="main">
            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag"/>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary mb-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new story
</a>
<div class="collapse" id="collapseExample">
    <div class="input-group mb-2">
        <form method="post" enctype="multipart/form-data">
            <p class="card text-break" style="width: 70rem; height: 18rem;">
                <input type="text" name="text" placeholder="add your story"/>
            </p>
            <div class="input-group mb-2">
                <input type="text" name="tag" class="form-control" placeholder="tag"/>
            </div>
            <div class="input-group mb-2">
                <div class="custom-file">
                    <input type="file" name="file" class="form-control" placeholder="Choose file"/>

                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">Add</button>

        </form>
    </div>
</div>

<div class="row row-cols-1 row-cols-md-3 g-4 mx-0">
    <!div class="col">
    <#list notes as note>
    <div class="card text-bg-light border-primary mb-3">
        <#if note.filename??>
        <img src="/img/${note.filename}">
    </#if>
    <div class="m-2">
        <span>${note.text}</span>
        <i>${note.tag}</i>
        <div class="card-footer text-muted">
            <strong>${note.authorName}</strong>
        </div>
    </div>
</div>
<#else>
    <a class="btn btn-outline-secondary mt-5">
        No stories yet :(...
    </a>
</#list>
</div>
</@c.page>