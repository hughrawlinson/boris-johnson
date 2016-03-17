package improbable.natures

import improbable.corelib.natures.{BaseNature, NatureApplication, NatureDescription}
import improbable.corelibrary.transforms.TransformNature
import improbable.math.Coordinates
import improbable.papi.entity.behaviour.EntityBehaviourDescriptor
import improbable.util.EntityPrefabs.TREE

object TreeNature extends NatureDescription {

  override def dependencies: Set[NatureDescription] = Set(BaseNature, TransformNature)

  override def activeBehaviours: Set[EntityBehaviourDescriptor] = Set.empty

  def apply(initialPosition: Coordinates): NatureApplication = {
    application(
      natures = Seq(
        BaseNature(entityPrefab = TREE),
        TransformNature(initialPosition.toVector3d)
      )
    )
  }

}
