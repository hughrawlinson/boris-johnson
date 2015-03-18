package improbable.entitytemplates

import improbable.corelib.entity.nature.definitions.CoreLibPhysicalObject
import improbable.corelib.entity.nature.{NatureApplication, NatureDescription}
import improbable.demo.{Flammable, NewPropagateFlamesBehaviour}
import improbable.math._
import improbable.physical.NewMoveRandomlyBehaviour

object DemoBot extends NatureDescription {
  override def activeBehaviours = {
    Set(NewMoveRandomlyBehaviour(), NewPropagateFlamesBehaviour())
  }

  override val dependencies = Set[NatureDescription](CoreLibPhysicalObject)

  def apply(position: Vector3d, onFire: Boolean = false): NatureApplication = {
    application(
      states = Seq(Flammable(onFire)),
      natures = Seq(CoreLibPhysicalObject(position, drag = 0.2f))
    )
  }
}