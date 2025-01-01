<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Calibri, sans-serif;
            margin: 20px;
            line-height: 1.4;
        }

        .header {
            text-align: center;
            font-size: 18px;
            font-weight: bold;
        }

        .company-details {
            margin-top: 10px;
            font-size: 14px;
            text-align: center;
        }

        .tax-invoice-title {
            text-align: center;
            font-size: 16px;
            font-weight: bold;
            text-decoration: underline;
            margin-bottom: 10px;
        }

        .details {
            width: 100%;
            margin-bottom: 20px;
            border-collapse: collapse;
            border: 0;
        }

        .details td {
            text-align: left;
            vertical-align: center;
            border: 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
            border-left: 1px solid #000;
            border-top: 1px solid #000;
            border-bottom: 1px solid #000;
        }

        th{
            border-right: 1px solid #000;
            padding: 8px;
            text-align: center;
            vertical-align: center;
            background-color: #f0f0f0;
        }

        td {
            border-right: 1px solid #000;
            padding-left: 8px;
            padding-right: 8px;
            text-align: center;
            vertical-align: top;
        }

        th.no, td.no {
            width: 5%;
        }
        th.qty, td.qty,
        th.rate, td.rate{
            width: 10%;
        }
        th.amount, td.amount {
            width: 15%;
        }
        th.description, td.description {
            width: 60%;
            text-align: left;
        }
        th.description {
            text-align: center;
        }

        th.merged {
            background-color: #f0f0f0;
            border-bottom: 1px solid #000;
        }

        .bottom-border {
            border-bottom: 1px solid #000;
        }

        .bank-details {
            margin-top: 20px;
            border: 1px solid #000;
            padding: 10px;
        }

        .section-border {
            border: 1px solid #000;
        }
    </style>
</head>
<body>
<!-- Company Header -->
<div class="header">
    ${merchantCompanyName} <span style="font-size: 10px;">(${merchantRegistrationNo})</span>
</div>
<div class="company-details">
    ${merchantCompanyAddress} <br/>
    Tel: ${merchantTelNo!"-"} &nbsp;&nbsp; Fax: ${merchantFaxNo!"-"} &nbsp;&nbsp; Email: ${merchantEmail!"-"}
</div>

<!-- TAX INVOICE Title -->
<div><br/></div>
<div class="tax-invoice-title">TAX INVOICE</div>
<div><br/></div>

<!-- Customer and Invoice Details -->
<table class="details">
    <tr>
        <td><strong>M/S:</strong></td>
        <td>${customerCompanyName}</td>
        <td><strong>Date:</strong></td>
        <td>${createDate}</td>
    </tr>
    <tr>
        <td></td>
        <td>${customerCompanyAddress}</td>
        <td><strong>Inv No.:</strong></td>
        <td>${invoiceNo}</td>
    </tr>
    <tr>
        <td colspan="4"></td>
    </tr>
    <tr>
        <td><strong>Attn:</strong></td>
        <td>${customerPic}</td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td colspan="4"></td>
    </tr>
    <tr>
        <td><strong>Tel:</strong></td>
        <td>${customerTel!"-"} </td>
        <td><strong>Fax:</strong></td>
        <td>${customerFax!"-"}</td>
    </tr>
    <tr>
        <td><strong>Email:</strong></td>
        <td>${customerEmail!"-"}</td>
        <td></td>
        <td></td>
    </tr>
</table>

<!-- Product/Service Section with Border -->
<div class="section-border">
    <!-- Product/Service Table -->
    <table>
        <thead>
        <tr>
            <th rowspan="2" class="no merged">No.</th>
            <th rowspan="2" class="description merged">Description</th>
            <th rowspan="2" class="qty merged">Qty</th>
            <th colspan="2" class="merged">Total Amount (RM)</th>
        </tr>
        <tr>
            <th class="rate bottom-border">U. Rate</th>
            <th class="amount bottom-border">Amount</th>
        </tr>
        </thead>
        <tbody>
        <#list items as item>
            <tr>
                <td class="no">${item_index + 1}</td>
                <td class="description">${item.description}</td>
                <td class="qty">${item.quantity}</td>
                <td class="rate">${item.unitRate}</td>
                <td class="amount">${item.totalAmount}</td>
            </tr>
        </#list>
        </tbody>

        <!-- Total Section -->
        <tr>
            <td style="border-top: 1px solid #000"></td>
            <td style="text-align: right; border-top: 1px solid #000"><strong>Subtotal:</strong></td>
            <td style="border-top: 1px solid #000"></td>
            <td style="border-top: 1px solid #000"></td>
            <td style="border-top: 1px solid #000">${subtotal}</td>
        </tr>
        <tr>
            <td></td>
            <td style="text-align: right"><strong>Total Tax:</strong></td>
            <td></td>
            <td></td>
            <td>${totalTax}</td>
        </tr>
        <#if totalDiscount?string != "0.00">
            <tr>
                <td></td>
                <td style="text-align: right;"><strong>Total Discount:</strong></td>
                <td></td>
                <td></td>
                <td>${totalDiscount}</td>
            </tr>
        </#if>
        <tr>
            <td style="border-top: 3px double #000; border-bottom: 2px double #000"></td>
            <td style="text-align: right; border-top: 3px double #000; border-bottom: 2px double #000"><strong>Total Amount:</strong></td>
            <td style="border-top: 3px double #000; border-bottom: 2px double #000"></td>
            <td style="border-top: 3px double #000; border-bottom: 2px double #000"></td>
            <td style="border-top: 3px double #000; border-bottom: 2px double #000">${totalAmount}</td>
        </tr>
    </table>
</div>

<!-- Bank Details -->
<div class="bank-details" style="margin-top: 20px;">
    <strong>Bank Name:</strong> ${bankName!"-"}<br/>
    <strong>Bank Account No.:</strong> ${bankAccountNo!"-"}
</div>

</body>
</html>
