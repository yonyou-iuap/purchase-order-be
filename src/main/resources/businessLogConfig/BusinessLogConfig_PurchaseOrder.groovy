class BusinessLogConfig_PurchaseOrder {
    def context;
    def purchaseOrder_insertSelective() {
         "请购单主表PurchaseOrder：执行保存方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time},编码为${context._methodReturn.code},名称为${context._param0.name}"
    }

    def purchaseOrder_updateSelective() {
         "请购单主表PurchaseOrder：执行修改方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time},编码为${context._methodReturn.code},名称为${context._param0.name}"
    }

    def purchaseOrder_saveMultiple() {
         "请购单主表PurchaseOrder： 执行批量保存方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time}"
    }


    def purchaseOrder_deleteBatch() {
         "请购单主表PurchaseOrder：执行删除方法:IP地址为${context._ip},USER用户为${context._user},TIME操作时间为${context._time}"
    }


}