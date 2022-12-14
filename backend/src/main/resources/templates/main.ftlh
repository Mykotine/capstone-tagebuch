<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
<div>
    <@l.logout />
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="add your story"/>
        <input type="text" name="tag" placeholder="tag"/>
        <input type="file" name="file"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Add</button>
    </form>
</div>

<div>List of your stories</div>
<form method="get" action="main">
    <input type="text" name="filter" value="${filter?ifExists}"/>
    <button type="submit">Find</button>
</form>
<#list notes as note>
<div>
    <b>${note.id}</b>
    <span>${note.text}</span>
    <i>${note.tag}</i>
    <strong>${note.authorName}</strong>

    <div>
        <#if note.filename??>
        <img src="/img/${note.filename}">
    </#if>
</div>
</div>
<#else>
No notes
</#list>
</@c.page>