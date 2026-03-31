<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Item</h1>
        <form method="post" action="<c:url value='/items'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">Item Code</label>
                <input name="itemCode" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Item Name</label>
                <input name="itemName" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Category</label>
                <input name="category" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Unit Of Measure</label>
                <input name="unitOfMeasure" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Estimated Unit Price</label>
                <input name="estimatedUnitPrice" type="number" step="0.01" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Supplier ID</label>
                <input name="supplierId" type="number" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <label class="flex items-center gap-2 text-sm">
                <input type="checkbox" name="isActive" checked class="rounded border-stone-300">
                Active
            </label>
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Item</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Item List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">Code</th>
                <th class="py-2">Name</th>
                <th class="py-2">Supplier ID</th>
                <th class="py-2">Price</th>
                <th class="py-2">Active</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${items}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.itemId}</td>
                    <td class="py-2">${row.itemCode}</td>
                    <td class="py-2">${row.itemName}</td>
                    <td class="py-2">${row.supplierId}</td>
                    <td class="py-2">${row.estimatedUnitPrice}</td>
                    <td class="py-2">${row.active}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty items}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No items yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
