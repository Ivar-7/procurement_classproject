<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Department</h1>
        <form method="post" action="<c:url value='/departments'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">Department Name</label>
                <input name="deptName" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Budget Code</label>
                <input name="budgetCode" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Annual Budget</label>
                <input name="annualBudget" type="number" step="0.01" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Head Name</label>
                <input name="headName" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Email</label>
                <input name="email" type="email" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Department</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Department List</h2>
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
