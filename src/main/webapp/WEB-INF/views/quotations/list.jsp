<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Quotation</h1>
        <form method="post" action="<c:url value='/quotations'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">Quote Reference</label>
                <input name="quoteRef" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Requisition ID</label>
                <input name="reqId" type="number" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Supplier ID</label>
                <input name="supplierId" type="number" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div class="grid grid-cols-2 gap-3">
                <div>
                    <label class="mb-1 block text-sm font-medium">Quote Date</label>
                    <input name="quoteDate" type="date" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
                </div>
                <div>
                    <label class="mb-1 block text-sm font-medium">Validity Date</label>
                    <input name="validityDate" type="date" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
                </div>
            </div>
            <div class="grid grid-cols-3 gap-2">
                <input name="subtotal" type="number" step="0.01" placeholder="Subtotal" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="taxAmount" type="number" step="0.01" placeholder="Tax" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
                <input name="totalAmount" type="number" step="0.01" placeholder="Total" class="rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Status</label>
                <select name="status" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Requested" selected>Requested</option>
                    <option value="Received">Received</option>
                    <option value="Selected">Selected</option>
                    <option value="Rejected">Rejected</option>
                    <option value="Expired">Expired</option>
                </select>
            </div>
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Quotation</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Quotation List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">Reference</th>
                <th class="py-2">Req ID</th>
                <th class="py-2">Supplier ID</th>
                <th class="py-2">Total</th>
                <th class="py-2">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${quotations}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.quoteId}</td>
                    <td class="py-2">${row.quoteRef}</td>
                    <td class="py-2">${row.reqId}</td>
                    <td class="py-2">${row.supplierId}</td>
                    <td class="py-2">${row.totalAmount}</td>
                    <td class="py-2">${row.status}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty quotations}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No quotations yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
