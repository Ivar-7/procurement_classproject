<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-5 lg:grid-cols-3">
    <div class="panel p-5 lg:col-span-2">
        <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
            <h1 class="text-xl font-semibold">Departments</h1>
            <a href="<c:url value='/departments/form'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
                Open Full Form
            </a>
        </div>

        <div class="overflow-x-auto">
            <table class="min-w-full text-sm">
                <thead>
                <tr class="border-b border-stone-200 text-left">
                    <th class="py-2">ID</th>
                    <th class="py-2">Name</th>
                    <th class="py-2">Budget</th>
                    <th class="py-2">Head</th>
                    <th class="py-2">Email</th>
                    <th class="py-2">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="row" items="${departments}">
                    <tr class="border-b border-stone-100">
                        <td class="py-2">${row.deptId}</td>
                        <td class="py-2">${row.deptName}</td>
                        <td class="py-2">${row.annualBudget}</td>
                        <td class="py-2">${row.headName}</td>
                        <td class="py-2">${row.email}</td>
                        <td class="py-2">
                            <c:url var="editUrl" value="/departments">
                                <c:param name="editId" value="${row.deptId}"/>
                            </c:url>

                            <a href="${editUrl}" title="Edit" aria-label="Edit"
                               class="mr-2 inline-flex h-8 w-8 items-center justify-center rounded-md border border-stone-300 text-stone-700">
                                <span aria-hidden="true">&#9998;</span>
                                <span class="sr-only">Edit</span>
                            </a>

                            <form method="post" action="<c:url value='/departments/delete'/>" class="inline"
                                  onsubmit="return confirm('Delete this department?');">
                                <input type="hidden" name="deptId" value="${row.deptId}" />
                                <button type="submit" title="Delete" aria-label="Delete"
                                        class="inline-flex h-8 w-8 items-center justify-center rounded-md border border-rose-300 text-rose-700">
                                    <span aria-hidden="true">&#128465;</span>
                                    <span class="sr-only">Delete</span>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty departments}">
                    <tr>
                        <td colspan="6" class="py-4 text-center text-stone-500">No departments yet.</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <div>
        <jsp:include page="form.jsp">
            <jsp:param name="embed" value="true"/>
        </jsp:include>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
