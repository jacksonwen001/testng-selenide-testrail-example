package com.chancetop.testrail;

import com.chancetop.helpers.BaseConfig;
import com.codepine.api.testrail.TestRail;
import com.codepine.api.testrail.model.Project;
import com.codepine.api.testrail.model.Result;
import com.codepine.api.testrail.model.ResultField;
import com.codepine.api.testrail.model.Run;
import org.aeonbits.owner.ConfigFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestRailReport {
    private static List<Result> results = new ArrayList<>();
    private static BaseConfig config = ConfigFactory.create(BaseConfig.class);

    public static void addResult(Result result) {
        results.add(result);
    }

    public static void reportResult(int projectId, int suiteId, String theNameOfTestRun){
        TestRail testRail = TestRail.builder(config.testrailUrl(), config.username(), config.apiKey()).build();
        LocalDateTime localDateTime = LocalDateTime.now();
        Project project = testRail.projects().get(projectId).execute();
        String localDateTimeFormatter = localDateTime.format(DateTimeFormatter.ofPattern("M/d/y H:m:ss"));
        Run run = testRail.runs().add(
                project.getId(),
                new Run().setName(theNameOfTestRun + "_" + localDateTimeFormatter)
                        .setIncludeAll(false)
                        .setSuiteId(suiteId)
                        .setCaseIds(results.stream().map(result -> result.getCaseId()).collect(Collectors.toList()))
        ).execute();
        List<ResultField> resultFields = testRail.resultFields().list().execute();
        testRail.results().addForCases(run.getId(), results, resultFields).execute();
    }
}
