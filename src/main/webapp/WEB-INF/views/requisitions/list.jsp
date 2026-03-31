<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="grid gap-4 lg:grid-cols-3">
    <div class="panel p-5">
        <h1 class="text-xl font-semibold">Add Requisition</h1>
        <form method="post" action="<c:url value='/requisitions'/>" class="mt-4 space-y-3">
            <div>
                <label class="mb-1 block text-sm font-medium">Requisition Number</label>
                <input name="reqNumber" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Department ID</label>
                <input name="deptId" type="number" required class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Requested By</label>
                <input name="requestedBy" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Request Date</label>
                <input name="requestDate" type="date" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Total Estimated</label>
                <input name="totalEstimated" type="number" step="0.01" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm" />
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Urgency</label>
                <select name="urgency" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Low">Low</option>
                    <option value="Medium" selected>Medium</option>
                    <option value="High">High</option>
                    <option value="Critical">Critical</option>
                </select>
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Status</label>
                <select name="status" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm">
                    <option value="Draft" selected>Draft</option>
                    <option value="Pending">Pending</option>
                    <option value="Approved">Approved</option>
                    <option value="Rejected">Rejected</option>
                    <option value="Converted">Converted</option>
                </select>
            </div>
            <div>
                <label class="mb-1 block text-sm font-medium">Justification</label>
                <textarea name="justification" rows="3" class="w-full rounded-md border border-stone-300 px-3 py-2 text-sm"></textarea>
            </div>
            <button class="rounded-md bg-teal-700 px-4 py-2 text-sm font-medium text-white">Save Requisition</button>
        </form>
    </div>

    <div class="panel overflow-x-auto p-5 lg:col-span-2">
        <h2 class="mb-3 text-lg font-semibold">Requisition List</h2>
        <table class="min-w-full text-sm">
            <thead>
            <tr class="border-b border-stone-200 text-left">
                <th class="py-2">ID</th>
                <th class="py-2">Req No.</th>
                <th class="py-2">Dept ID</th>
                <th class="py-2">Status</th>
                <th class="py-2">Urgency</th>
                <th class="py-2">Total</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${requisitions}">
                <tr class="border-b border-stone-100">
                    <td class="py-2">${row.reqId}</td>
                    <td class="py-2">${row.reqNumber}</td>
                    <td class="py-2">${row.deptId}</td>
                    <td class="py-2">${row.status}</td>
                    <td class="py-2">${row.urgency}</td>
                    <td class="py-2">${row.totalEstimated}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty requisitions}">
                <tr>
                    <td colspan="6" class="py-4 text-center text-stone-500">No requisitions yet.</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
