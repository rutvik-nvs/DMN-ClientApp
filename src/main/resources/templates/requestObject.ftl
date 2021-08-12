{
    Input: [
        <#-- list ${processedArray.inputArray} as inputItem}>
        {
            "DOM": "${inputItem.DOM}",
            "hasLicense?": ${inputItem.hasLicense?},
            "isLicenseValid?": ${inputItem.isLicenseValid?}
        },
        </#list -->

        <#assign Array = { "inputArray": "1" } >
        ${Array.inputArray}
    ]
}