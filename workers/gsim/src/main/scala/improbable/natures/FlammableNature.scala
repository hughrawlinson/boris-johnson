package improbable.natures

import improbable.behaviours.color.SetColorFromFireBehaviour
import improbable.behaviours.physical.{FireIncreaseTemperatureBehaviour, PropagateFireBehaviour}
import improbable.corelib.natures.{NatureApplication, NatureDescription}
import improbable.physical.Fire

object FlammableNature extends NatureDescription {
  override val dependencies = Set[NatureDescription]()

  override def activeBehaviours = {
    Set(
      descriptorOf[PropagateFireBehaviour],
      descriptorOf[SetColorFromFireBehaviour],
      descriptorOf[FireIncreaseTemperatureBehaviour]
    )
  }

  def apply(onFire: Boolean = false): NatureApplication = {
    application(
      states = Seq(
        Fire(onFire, 32)
      )
    )
  }
}