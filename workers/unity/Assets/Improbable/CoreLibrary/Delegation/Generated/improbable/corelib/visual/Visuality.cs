// Generated by SpatialOS codegen. DO NOT EDIT!
// source: schema.improbable.corelib.visual.VisualityData in improbable/corelib/visual/visuality.proto.

using System;
using Improbable.Core.Serialization;
using Improbable.Entity.State;

namespace Improbable.Corelib.Visual
{
[ReaderInterface]
[CanonicalName("improbable.corelib.visual.Visuality", 190114)]
public interface VisualityReader : IEntityStateReader
{
    bool IsVisual { get; }

    event System.Action<bool> IsVisualUpdated;
}

public interface IVisualityUpdater : IEntityStateUpdater
{
    void FinishAndSend();
    IVisualityUpdater IsVisual(bool newValue);
}

[WriterInterface]
[CanonicalName("improbable.corelib.visual.Visuality", 190114)]
public interface VisualityWriter : VisualityReader, IUpdateable<IVisualityUpdater> { }

public class Visuality : global::Improbable.Entity.State.StateBase<Improbable.Corelib.Visual.VisualityData, Schema.Improbable.Corelib.Visual.VisualityData>, VisualityWriter, IVisualityUpdater
{
    public Visuality(global::Improbable.EntityId entityId, Improbable.Corelib.Visual.VisualityData data, IStateSender sender)
        : base(entityId, data, sender, Improbable.Corelib.Visual.VisualityDataHelper.Instance) { }
    private static log4net.ILog LOGGER = log4net.LogManager.GetLogger(typeof(Visuality));
    private static bool ShouldLogFinishAndSendNoUpdate = true;
    private static bool ShouldLogUpdateNoFinishAndSend = true;

    protected override void LogFinishAndSendWithNoUpdate() {
        if (ShouldLogFinishAndSendNoUpdate)
        {
            ShouldLogFinishAndSendNoUpdate = false;
            LOGGER.ErrorFormat("Finish and send was called with no update in flight for entity {0}. " +
                               "This is probably due to having more StateUpdates in flight, which is an error. (Logged only once.)", EntityId);
        }
    }

    public bool IsVisual { get { return Data.IsVisual; } }

    private readonly global::System.Collections.Generic.List<System.Action<bool>> updatedCallbacksIsVisual =
        new global::System.Collections.Generic.List<System.Action<bool>>();
    public event System.Action<bool> IsVisualUpdated
    {
        add
        {
            updatedCallbacksIsVisual.Add(value);
            value(Data.IsVisual);
        }
        remove { updatedCallbacksIsVisual.Remove(value); }
    }

    override protected void UnsubscribeEventHandlersInternal(object visualizer)
    {
        UnsubscribeEventHandler(visualizer, updatedCallbacksIsVisual);
    }

    public IVisualityUpdater Update
    {
        get
        {
            if (Updating)
            {
                if (ShouldLogUpdateNoFinishAndSend)
                {
                    ShouldLogUpdateNoFinishAndSend = false;
                    LOGGER.ErrorFormat("Multiple state updates of entity {0} are in flight, which has undefined semantics. " +
                        "Each call to Update has to be followed by a FinishAndSend() before another call is made on the same state. (Logged only once.)", EntityId);
                }
            }
            else
            {
                Updating = true;
                Updater = new VisualityUpdate(EntityId, new bool[0], new Schema.Improbable.Corelib.Visual.VisualityData());
            }
            return this;
        }
    }

    IVisualityUpdater IVisualityUpdater.IsVisual(bool newValue)
    {
        if (Updater.Proto.IsVisualSpecified || !IsVisual.Equals(newValue))
        {
            Updater.Proto.IsVisual = newValue;
        }
        return this;
    }

    override protected bool TriggerUpdatedEvents(Schema.Improbable.Corelib.Visual.VisualityData update, bool[] statesToClear)
    {
        bool anythingUpdated = false;
        bool updatedIsVisual = update.IsVisualSpecified;
        anythingUpdated |= updatedIsVisual;
        if (updatedIsVisual) TriggerCallbacks(updatedCallbacksIsVisual, Data.IsVisual);

        if (anythingUpdated) TriggerPropertyUpdated();
        return anythingUpdated;
    }

    override protected bool TriggerAllStateEvents(Schema.Improbable.Corelib.Visual.VisualityData stateUpdate)
    {
        bool anythingUpdated = false;
        return anythingUpdated;
    }

    override protected bool TriggerAllStateEvents()
    {
        bool anythingUpdated = false;
        return anythingUpdated;
    }
}

public class VisualityUpdate : global::Improbable.Entity.State.StateUpdate<Improbable.Corelib.Visual.VisualityData, Schema.Improbable.Corelib.Visual.VisualityData>
{
    public const uint COMPONENT_ID = 190114;
    public VisualityUpdate(global::Improbable.EntityId entityId, bool[] statesToClear, Schema.Improbable.Corelib.Visual.VisualityData proto)
        : base(entityId, statesToClear, Improbable.Corelib.Visual.VisualityDataHelper.Instance, proto, COMPONENT_ID) { }

    public override IReadWriteEntityState CreateState(global::Improbable.EntityId entityId, IStateSender stateSender)
    {
        return new Visuality(entityId, GetData(), stateSender);
    }

    public static VisualityUpdate ExtractFrom(global::Improbable.Protocol.ComponentUpdate proto)
    {
        var protoState = ProtoBuf.Extensible.GetValue<Schema.Improbable.Corelib.Visual.VisualityData>(proto.ComponentData, (int) COMPONENT_ID);
        return new VisualityUpdate(global::Improbable.EntityIdHelper.Instance.FromProto(proto.EntityId), null, protoState);
    }

    override protected int SeqToId(int seqId) { return seqToId[seqId]; }
    private static int[] seqToId = {};
}
}
