<div class="page_style">
	<span class="total">Total ${paginationObjs.totalPages} Pages</span>
<#if paginationObjs.firstPage>
	<span class="disabled"> <img src="${base}/images/dis_first.png" class="img_position" /> Prev </span>
<#else>
	<a href="${base}/${pageLink}?pageNo=1"> <img src="${base}/images/first.png" class="img_position" /> First </a>
	<a href="${base}/${pageLink}?pageNo=${paginationObjs.prevPage?c}"> <img src="${base}/images/prev.png" class="img_position" /> Prev </a>
</#if>
<#if paginationObjs.pageNo-5 gt 1>
	<#if paginationObjs.totalPages gt paginationObjs.pageNo+4>
		<#list paginationObjs.pageNo-5..paginationObjs.pageNo+4 as i>
			<#if i == paginationObjs.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	<#else>
		<#list paginationObjs.totalPages-9..paginationObjs.totalPages as i>
			<#if i == paginationObjs.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	</#if>
<#else>
	<#if paginationObjs.totalPages gt 10>
		<#list 1..10 as i>
			<#if i == paginationObjs.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	<#else>
		<#list 1..paginationObjs.totalPages as i>
			<#if i == paginationObjs.pageNo>
				<span class="current">${i?c}</span>
			<#else>
				<a href="${base}/${pageLink}?pageNo=<#if i gt 0>${i?c}</#if>">${i?c}</a>
			</#if>
		</#list>
	</#if>
</#if>
<#if paginationObjs.lastPage>
	<span class="disabled"> Next <img src="${base}/images/dis_next.png" class="img_position" /> </span><span class="disabled"> Last <img src="${base}/images/dis_last.png" class="img_position" /> </span>
<#else>
	<a href="${base}/${pageLink}?pageNo=${paginationObjs.nextPage?c}"> Next <img src="${base}/images/next.png" class="img_position" /> </a>
	<a href="${base}/${pageLink}?pageNo=${paginationObjs.totalPages?c}"> Last <img src="${base}/images/last.png" class="img_position" /> </a>
</#if>
</div>