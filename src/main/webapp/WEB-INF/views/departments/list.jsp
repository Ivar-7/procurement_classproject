<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Departments</h1>
        <a href="<c:url value='/departments/form'/>" class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">
            Go To Form
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
                </tr>
            </c:forEach>
            <c:if test="${empty departments}">
                <tr>
                    <td colspan="5" class="py-4 text-center text-stone-500">No departments yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
