<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Payment</h1>
        <form method="post" action="<c:url value='/payments'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">Payment Reference</label>
                <input name="paymentRef" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-2 gap-2">
                <input name="poId" type="number" required placeholder="PO ID" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="grnId" type="number" placeholder="GRN ID" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <input name="invoiceNumber" placeholder="Invoice Number" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <div class="grid grid-cols-2 gap-2">
                <input name="invoiceAmount" type="number" step="0.01" placeholder="Invoice Amount" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="amountPaid" type="number" step="0.01" placeholder="Amount Paid" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-2 gap-2">
                <input name="paymentDate" type="date" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <select name="paymentMethod" class="rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Bank Transfer" selected>Bank Transfer</option>
                    <option value="Check">Check</option>
                    <option value="Cash">Cash</option>
                    <option value="Card">Card</option>
                </select>
            </div>
            <input name="transactionRef" placeholder="Transaction Reference" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <div>
                <label class="mb-1 block text-sm font-medium">Status</label>
                <select name="status" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Pending" selected>Pending</option>
                    <option value="Processing">Processing</option>
                    <option value="Completed">Completed</option>
                    <option value="Failed">Failed</option>
                </select>
            </div>
            <input name="processedBy" placeholder="Processed By" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Payment</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Payment List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">Reference</th>
                <th class="py-2">PO ID</th>
                <th class="py-2">Amount Paid</th>
                <th class="py-2">Status</th>
                <th class="py-2">Date</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${payments}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.paymentId}</td>
                    <td class="py-2">${row.paymentRef}</td>
                    <td class="py-2">${row.poId}</td>
                    <td class="py-2">${row.amountPaid}</td>
                    <td class="py-2">${row.status}</td>
                    <td class="py-2">${row.paymentDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty payments}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No payments yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
