<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Goods Receipt</h1>
        <form method="post" action="<c:url value='/goods-receipts'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">GRN Number</label>
                <input name="grnNumber" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">PO ID</label>
                <input name="poId" type="number" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Receipt Date</label>
                <input name="receiptDate" type="date" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <input name="receivedBy" placeholder="Received By" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <input name="deliveryNoteRef" placeholder="Delivery Note Ref" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <input name="totalItems" type="number" placeholder="Total Items" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <div>
                <label class="mb-1 block text-sm font-medium">Status</label>
                <select name="status" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Pending" selected>Pending</option>
                    <option value="Complete">Complete</option>
                    <option value="Partial">Partial</option>
                    <option value="Rejected">Rejected</option>
                </select>
            </div>
            <textarea name="notes" rows="2" placeholder="Notes" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm"></textarea>
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Goods Receipt</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Goods Receipt List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">GRN Number</th>
                <th class="py-2">PO ID</th>
                <th class="py-2">Total Items</th>
                <th class="py-2">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${goodsReceipts}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.grnId}</td>
                    <td class="py-2">${row.grnNumber}</td>
                    <td class="py-2">${row.poId}</td>
                    <td class="py-2">${row.totalItems}</td>
                    <td class="py-2">${row.status}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty goodsReceipts}">
                <tr>
                    <td colspan="5" class="py-4 text-center text-stone-500">No goods receipts yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
