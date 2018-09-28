package eu.virtuwind.resourcemanager.impl;


import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.AddMeterInput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.AddMeterOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.service.rev130918.SalMeterService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.meter.types.rev130918.MeterId;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


public class ResourceManager {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceManager.class);

    private SalMeterService salMeterService;

    public ResourceManager(SalMeterService salMeterService1) {
        this.salMeterService =salMeterService1;

        /*VTNMeterUtils vtnMeterUtils =
                new VTNMeterUtils(new MeterId(1L), 1000L, 100L);

        AddMeterInput addMeterInput = vtnMeterUtils.createAddMeterInput();



        Future<RpcResult<AddMeterOutput>> future =  salMeterService.addMeter(addMeterInput);

        System.out.println("isDone" + future.isDone());
        try {
            System.out.println("IsSuccessful:" + future.get().isSuccessful());
            System.out.println(future.get().getErrors().size());
            System.out.println(future.get().getErrors().toString());
            System.out.println("\n\n\n\n" + future.get().getResult().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


    }

}
