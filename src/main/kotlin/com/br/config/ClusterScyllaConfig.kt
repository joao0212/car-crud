package com.br.config

import com.datastax.driver.core.Cluster

internal object ClusterScyllaConfig {

    fun buildCluster(): Cluster =
        Cluster.builder()
            .withoutJMXReporting()
            .withClusterName("myCluster")
            .addContactPoint("0.0.0.0")
            .build()
}