package com.pixelsci.retailer.service;

import com.pixelsci.retailer.dto.RetailerMapper;
import com.pixelsci.retailer.dto.RetailerRecord;
import com.pixelsci.retailer.model.Retailer;
import com.pixelsci.retailer.repository.RetailerRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public record RetailerService(RetailerRepository retailerRepository) {

    public List<RetailerRecord> getAllRetailers() {

        Pageable request= PageRequest.of(0,10,Sort.by("owerName").ascending());
        Page<Retailer> retailers= retailerRepository.findAll(request);
        return retailers.stream().map(RetailerMapper::toRetailerRecord).toList();

        /*
        var pageble = Pageable.ofSize(10).withPage(0);
        return  retailerRepository.findAll(pageble);*/
    }

    public RetailerRecord getRetailer(Long id) {
        return retailerRepository.findById(id)
                .map(RetailerMapper::toRetailerRecord)
                .orElseThrow(() -> new IllegalStateException("not found"));
    }

   /*
    public RetailerRecord getRetailer(Long id) {
        RetailerRecord record =null;
        retailerRepository.findById(id).ifPresentOrElse(
                (item)-> record= RetailerMapper.toRetailerRecord(item),
                ()->{ throw  new IllegalStateException("not found"); } );
        return record;
    }*/

   /* @Transactional
    public void createRetailer(RetailerRecord retailerRecord) {


          Retailer tempRetailer = new Retailer();
            tempRetailer.setEmail(retailerRecord.email());
            tempRetailer.setPhone(retailerRecord.phone());

         //retailerRepository.findAll( Example.of(tempRetailer,ExampleMatcher.matchingAny()));
         retailerRepository.findOne(Example.of(tempRetailer,ExampleMatcher.matchingAny())).ifPresentOrElse(
                 (item)-> {throw new IllegalStateException("Retailer already found");},
                 () -> retailerRepository.save(RetailerMapper.toRetailer(retailerRecord)));
    }*/

    public RetailerRecord createRetailer(RetailerRecord retailerRecord) {
        Retailer tempRetailer = new Retailer();
        tempRetailer.setEmail(retailerRecord.email());
        tempRetailer.setPhone(retailerRecord.phone());

        boolean exists = retailerRepository.exists(Example.of(tempRetailer, ExampleMatcher.matchingAny()));
        if (exists) {
            throw new IllegalStateException("Retailer already found");
        }

        Retailer retailer= retailerRepository.save(RetailerMapper.toRetailer(retailerRecord));
        return RetailerMapper.toRetailerRecord(retailer);
    }


    public RetailerRecord updateRetailer(RetailerRecord retailerRecord) {

        /*
        retailerRepository.findByEmailOrPhone(retailerRecord.email(),
               retailerRecord.phone()).ifPresentOrElse( item-> {
                     item.setPhone(retailerRecord.phone());
                     item.setEmail(retailerRecord.email());
                     item.setAddress(retailerRecord.address());
                     retailerRepository.save(item); } ,
                ()-> {
                    throw new IllegalStateException("Retailer already found");
                } );
        */

        Retailer retailer= retailerRepository.findByEmailOrPhone(
               retailerRecord.email(),
                retailerRecord.phone())
               .orElseThrow( ()-> new IllegalStateException("Retailer not found"));


        retailer.setPhone(retailerRecord.phone());
        retailer.setEmail(retailerRecord.email());
        retailer.setAddress(retailerRecord.address());
        Retailer savedretailer=  retailerRepository.save(retailer);
        return RetailerMapper.toRetailerRecord(savedretailer);

    }

    public void deleteRetailer(RetailerRecord retailerRecord) {

        retailerRepository.findByEmailOrPhone(retailerRecord.email(),
                retailerRecord.phone()).ifPresentOrElse( item-> {
                        retailerRepository.deleteById(item.getId());
                     } ,
                ()-> {
                    throw new IllegalStateException("Retailer not found");
                } );
    }
}
