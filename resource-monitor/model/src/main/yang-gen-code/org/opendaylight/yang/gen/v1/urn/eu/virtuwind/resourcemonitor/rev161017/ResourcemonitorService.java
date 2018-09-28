package org.opendaylight.yang.gen.v1.urn.eu.virtuwind.resourcemonitor.rev161017;
import org.opendaylight.yangtools.yang.binding.RpcService;
import org.opendaylight.yangtools.yang.common.RpcResult;
import java.util.concurrent.Future;


/**
 * Interface for implementing the following YANG RPCs defined in module <b>resourcemonitor</b>
 * <pre>
 * rpc get-stats {
 *     output {
 *         leaf stats {
 *             type string;
 *         }
 *     }
 * }
 * rpc get-number-of-packets {
 *     input {
 *         leaf node-connector-id {
 *             type string;
 *         }
 *     }
 *     
 *     output {
 *         leaf transmitted {
 *             type decimal64;
 *         }
 *         leaf received {
 *             type decimal64;
 *         }
 *     }
 * }
 * </pre>
 *
 */
public interface ResourcemonitorService
    extends
    RpcService
{




    /**
     * @return <code>java.util.concurrent.Future</code> <code>stats</code>, or <code>null</code> if not present
     */
    Future<RpcResult<GetStatsOutput>> getStats();
    
    /**
     * @return <code>java.util.concurrent.Future</code> <code>numberOfPackets</code>, or <code>null</code> if not present
     */
    Future<RpcResult<GetNumberOfPacketsOutput>> getNumberOfPackets(GetNumberOfPacketsInput input);

}

