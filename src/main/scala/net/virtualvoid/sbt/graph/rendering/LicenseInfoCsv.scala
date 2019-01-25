package net.virtualvoid.sbt.graph.rendering

import net.virtualvoid.sbt.graph.ModuleGraph

object LicenseInfoCsv {
  def render(graph: ModuleGraph): String =
    graph.nodes.filter(_.isUsed).groupBy(_.license).toSeq.sortBy(_._1).map {
      case (license, modules) ⇒
        val orderedOrganizations = modules
          .map(module ⇒ {
            s"${module.id.organisation};${module.id.name}"
          })
          .distinct
          .sorted

        orderedOrganizations.map {
          organisation ⇒ license.getOrElse("No license specified") + ";" + organisation
        }.mkString("\n")
    }.mkString("\n\n")
}
