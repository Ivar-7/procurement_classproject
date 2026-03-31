<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Supplier</h1>
        <form method="post" action="<c:url value='/suppliers'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">Company Name</label>
                <input name="companyName" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Contact Person</label>
                <input name="contactPerson" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Phone</label>
                <input name="phone" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Email</label>
                <input name="email" type="email" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Category</label>
                <input name="category" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Status</label>
                <select name="status" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Active">Active</option>
                    <option value="Pending">Pending</option>
                    <option value="Blacklisted">Blacklisted</option>
                </select>
            </div>
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Supplier</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Supplier List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">Company</th>
                <th class="py-2">Contact</th>
                <th class="py-2">Category</th>
                <th class="py-2">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${suppliers}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.supplierId}</td>
                    <td class="py-2">${row.companyName}</td>
                    <td class="py-2">${row.contactPerson}</td>
                    <td class="py-2">${row.category}</td>
                    <td class="py-2">${row.status}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty suppliers}">
                <tr>
                    <td colspan="5" class="py-4 text-center text-stone-500">No suppliers yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
