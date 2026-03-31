<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Purchase Order</h1>
        <form method="post" action="<c:url value='/purchase-orders'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">PO Number</label>
                <input name="poNumber" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-2 gap-2">
                <input name="reqId" type="number" placeholder="Req ID" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="quoteId" type="number" placeholder="Quote ID" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-2 gap-2">
                <input name="supplierId" type="number" required placeholder="Supplier ID" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="deptId" type="number" required placeholder="Dept ID" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-2 gap-2">
                <input name="orderDate" type="date" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="deliveryDate" type="date" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-3 gap-2">
                <input name="subtotal" type="number" step="0.01" placeholder="Subtotal" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="taxAmount" type="number" step="0.01" placeholder="Tax" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="totalAmount" type="number" step="0.01" placeholder="Total" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Status</label>
                <select name="status" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Draft" selected>Draft</option>
                    <option value="Sent">Sent</option>
                    <option value="Acknowledged">Acknowledged</option>
                    <option value="Partial">Partial</option>
                    <option value="Completed">Completed</option>
                    <option value="Cancelled">Cancelled</option>
                </select>
            </div>
            <textarea name="notes" rows="2" placeholder="Notes" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm"></textarea>
            <input name="createdBy" placeholder="Created By" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Purchase Order</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Purchase Order List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">PO Number</th>
                <th class="py-2">Supplier ID</th>
                <th class="py-2">Dept ID</th>
                <th class="py-2">Total</th>
                <th class="py-2">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${purchaseOrders}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.poId}</td>
                    <td class="py-2">${row.poNumber}</td>
                    <td class="py-2">${row.supplierId}</td>
                    <td class="py-2">${row.deptId}</td>
                    <td class="py-2">${row.totalAmount}</td>
                    <td class="py-2">${row.status}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty purchaseOrders}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No purchase orders yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
