import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        List<Long> seeds = new ArrayList<>();
        HashMap<long[], Long> seed_soil = new HashMap<>();
        HashMap<long[], Long> soil_fert = new HashMap<>();
        HashMap<long[], Long> fert_water = new HashMap<>();
        HashMap<long[], Long> water_light = new HashMap<>();
        HashMap<long[], Long> light_temp = new HashMap<>();
        HashMap<long[], Long> temp_humi = new HashMap<>();
        HashMap<long[], Long> humi_loca = new HashMap<>();
        String line = "";
        while(br.ready()){
            if(!line.isEmpty()){
                if(line.contains("seeds:")){
                    line = line.split(":")[1].strip();
                    for(String num : line.split(" ")){
                        seeds.add(Long.parseLong(num));
                    }
                    line = br.readLine();
                } else if(line.contains("seed-to-soil")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, seed_soil);
                        }
                        
                    }
                } else if(line.contains("soil-to-fertilizer")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, soil_fert);
                        }
                        
                    }
                } else if(line.contains("fertilizer-to-water")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, fert_water);
                        }
                        
                    }
                } else if(line.contains("water-to-light")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, water_light);
                        }
                    }
                } else if(line.contains("light-to-temperature")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, light_temp);
                        }
                    }
                } else if(line.contains("temperature-to-humidity")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, temp_humi);
                        }
                    }
                } else if(line.contains("humidity-to-location")){
                    while(br.ready() && !line.isEmpty()){
                        line = br.readLine();
                        if(!line.isEmpty()){
                            buildMap(line, humi_loca);
                        }
                    }
                }
            } else {
                line = br.readLine();
            }
        }
        br.close();
        long min = Long.MAX_VALUE;
        for(long seed : seeds){
            long soil = check(seed, seed_soil);
            long fert = check(soil, soil_fert);
            long water = check(fert, fert_water);;
            long light = check(water, water_light);;
            long temp = check(light, light_temp);
            long humi = check(temp, temp_humi);
            long loca = check(humi, humi_loca);

            min = Math.min(loca, min);
        }
        System.out.println(min);
    }

    static void buildMap(String str, HashMap<long[], Long> map){
        //"50 98 2"
        String[] nums = str.split(" ");
        long des = Long.parseLong(nums[0]);
        long source = Long.parseLong(nums[1]);
        long count = Long.parseLong(nums[2]);

        long[] scale = new long[]{source, source + count - 1};
        map.put(scale, des);
    }

    static long check(long key, HashMap<long[], Long> map){
        for(Map.Entry<long[], Long> e : map.entrySet()){
            if(key <= e.getKey()[1] && key >= e.getKey()[0]){
                return e.getValue() + key - e.getKey()[0];
            }
        }
        return key;
    }
}
