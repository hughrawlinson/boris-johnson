package improbable.natures

import improbable.corelib.entity.nature.definitions.RigidbodyEntity
import improbable.corelib.entity.nature.{NatureApplication, NatureDescription}
import improbable.math._
import improbable.papi.entity.EntityPrefab
import improbable.physical.NewMoveRandomlyBehaviour

object Bot extends NatureDescription {
  override val dependencies = Set[NatureDescription](RigidbodyEntity, Colored)

  override def activeBehaviours = {
    Set(NewMoveRandomlyBehaviour())
  }

  def apply(position: Coordinates, onFire: Boolean = false): NatureApplication = {
    application(
      states = Seq(),
      natures = Seq(
        RigidbodyEntity(EntityPrefab("Cube"), position, drag = 0.2f),
        Colored(java.awt.Color.red))
    )
  }
}