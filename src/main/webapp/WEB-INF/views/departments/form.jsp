<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/components/header.jspf" %>

<section class="panel p-5">
    <div class="mb-4 flex flex-wrap items-center justify-between gap-3">
        <h1 class="text-xl font-semibold">Department Form</h1>
        <a href="<c:url value='/departments'/>" class="rounded-md border border-stone-300 px-4 py-2 text-sm font-medium text-stone-700">
            Back To List
        </a>
    </div>

    <form method="post" action="<c:url value='/departments'/>" class="space-y-3">
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
</section>

<%@ include file="/WEB-INF/views/components/footer.jspf" %>
