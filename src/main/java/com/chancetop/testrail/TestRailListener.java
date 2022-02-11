package com.chancetop.testrail;

import com.codepine.api.testrail.model.Result;
import org.testng.*;


import static com.chancetop.testrail.TestRailReport.addResult;
import static com.chancetop.testrail.TestRailReport.reportResult;

public class TestRailListener implements ITestListener {
    private ITestContext testContext;

    @Override
    public void onTestSuccess(ITestResult result) {
//        ITestListener.super.onTestSuccess(result);
        addTestResult(result, TestRailStatus.PASSED);
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        ITestListener.super.onTestFailure(result);
        addTestResult(result, TestRailStatus.FAILED);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        ITestListener.super.onTestSkipped(result);
        addTestResult(result, TestRailStatus.UNTESTED);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        this.testContext = context;

    }

    @Override
    public void onFinish(ITestContext context) {
//        ITestListener.super.onFinish(context);
        reportResult(2, 1, context.getName());
    }


    private void addTestResult(ITestResult result, TestRailStatus testRailStatus){
        TestRail testRail = result.getTestClass().getRealClass().getAnnotation(TestRail.class);
        if(testRail != null) {
            int caseId = testRail.caseId();
            addResult(new Result().setCaseId(caseId).setTestId(caseId).setStatusId(testRailStatus.getStatus()));
        }
    }

}
