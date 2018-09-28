package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemanager.rev161017;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.binding.RpcService;
import java.util.concurrent.Future;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>resourcemanager</b>
 * <pre>
 * rpc embedMplsFlow {
 *     input {
 *         leaf mpls-label {
 *             type int32;
 *         }
 *         leaf dst-mac {
 *             type string;
 *         }
 *         leaf mac-to-modify {
 *             type string;
 *         }
 *         leaf queue-id {
 *             type int32;
 *         }
 *     }
 *     
 *     output {
 *         leaf response {
 *             type string;
 *         }
 *     }
 * }
 * rpc best-embeded-flow {
 *     input {
 *         leaf sourceIP {
 *             type string;
 *         }
 *         leaf destinationIP {
 *             type string;
 *         }
 *         leaf source-port {
 *             type int32;
 *         }
 *         leaf dest-port {
 *             type int32;
 *         }
 *         leaf protocol {
 *             type int32;
 *         }
 *         list physical-path-links {
 *             key     leaf link {
 *                 type string;
 *             }
 *         }
 *         leaf idletimeout {
 *             type int32;
 *         }
 *         leaf hardtimeout {
 *             type int32;
 *         }
 *         list meters {
 *             key     leaf meter {
 *                 type int32;
 *             }
 *         }
 *         leaf src-node {
 *             type string;
 *         }
 *         leaf dest-node {
 *             type string;
 *         }
 *         leaf src-node-port {
 *             type string;
 *         }
 *         leaf dest-node-port {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf response {
 *             type string;
 *         }
 *     }
 * }
 * rpc embedMplsPath {
 *     input {
 *         leaf mplsLabel {
 *             type int32;
 *         }
 *         list links {
 *             key     leaf link {
 *                 type string;
 *             }
 *         }
 *         leaf queue-id {
 *             type int32;
 *         }
 *     }
 *     
 *     output {
 *         leaf response {
 *             type string;
 *         }
 *     }
 * }
 * rpc create-and-send-flow {
 *     input {
 *         leaf sourceIP {
 *             type string;
 *         }
 *         leaf destinationIP {
 *             type string;
 *         }
 *         leaf source-port {
 *             type int32;
 *         }
 *         leaf dest-port {
 *             type int32;
 *         }
 *         leaf protocol {
 *             type int32;
 *         }
 *         list physical-path-links {
 *             key     leaf link {
 *                 type string;
 *             }
 *         }
 *         list queues-on-path {
 *             key     leaf queue {
 *                 type int32;
 *             }
 *         }
 *         list meters {
 *             key     leaf meter {
 *                 type int32;
 *             }
 *         }
 *         leaf src-node {
 *             type string;
 *         }
 *         leaf dest-node {
 *             type string;
 *         }
 *         leaf src-node-port {
 *             type string;
 *         }
 *         leaf dest-node-port {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf response {
 *             type string;
 *         }
 *     }
 * }
 * rpc configure-fast-failover-groups {
 *     input {
 *         list path {
 *             key     leaf link {
 *                 type string;
 *             }
 *         }
 *         leaf ffport {
 *             type int32;
 *         }
 *     }
 *     
 *     output {
 *         leaf response {
 *             type string;
 *         }
 *     }
 * }
 * </pre>
 *
 */
public interface ResourcemanagerService
    extends
    RpcService
{




    Future<RpcResult<EmbedMplsFlowOutput>> embedMplsFlow(EmbedMplsFlowInput input);
    
    Future<RpcResult<BestEmbededFlowOutput>> bestEmbededFlow(BestEmbededFlowInput input);
    
    Future<RpcResult<EmbedMplsPathOutput>> embedMplsPath(EmbedMplsPathInput input);
    
    Future<RpcResult<CreateAndSendFlowOutput>> createAndSendFlow(CreateAndSendFlowInput input);
    
    Future<RpcResult<ConfigureFastFailoverGroupsOutput>> configureFastFailoverGroups(ConfigureFastFailoverGroupsInput input);

}

