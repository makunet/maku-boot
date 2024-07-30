import service from '@/utils/request'

export const use${FunctionName}Api = (id: number) => {
	return service.get('${requestUrl}/' + id)
}

<#if hasTree>
export const use${FunctionName}TreeLoadApi = (${treePid}: number) => {
	return service.get('${requestUrl}/list?${treePid}=' + ${treePid})
}

</#if>
export const use${FunctionName}SubmitApi = (dataForm: any) => {
	if (dataForm.id) {
		return service.put('${requestUrl}', dataForm)
	} else {
		return service.post('${requestUrl}', dataForm)
	}
}