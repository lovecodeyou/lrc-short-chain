function strIsNotEmpty(content) {
    return content && content.length > 0;
}

function strIsEmpty(content) {
    return !strIsNotEmpty(content);
}

function strEmptyToDefault(content, defaultContent) {
    return strIsNotEmpty(content) ? content : defaultContent;
}


function successMessage(message) {
    ElementPlus.ElMessage({
        showClose: true,
        message: message,
        type: 'success',
    })
}

function warningMessage(message) {
    ElementPlus.ElMessage({
        showClose: true,
        message: message,
        type: 'warning',
    })
}

function errorMessage(message) {
    ElementPlus.ElMessage({
        showClose: true,
        message: message,
        type: 'error',
    })
}