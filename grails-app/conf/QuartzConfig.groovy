
quartz {
    autoStartup = true
    jdbcStore = false
    waitForJobsToCompleteOnShutdown = true
    exposeSchedulerInRepository = false

    props {
        scheduler.skipUpdateCheck = true

        // jobStore.dataSource = 'quartz'
    }
}

environments {
    test {
        quartz {
            autoStartup = false
        }
    }
}
