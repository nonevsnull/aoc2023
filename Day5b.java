import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5b {
    static HashMap<long[], Long> seed_soil = new HashMap<>();
    static HashMap<long[], Long> soil_fert = new HashMap<>();
    static HashMap<long[], Long> fert_water = new HashMap<>();
    static HashMap<long[], Long> water_light = new HashMap<>();
    static HashMap<long[], Long> light_temp = new HashMap<>();
    static HashMap<long[], Long> temp_humi = new HashMap<>();
    static HashMap<long[], Long> humi_loca = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        List<Long> seeds = new ArrayList<>();
        
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
        for(int i = 0;i < seeds.size() - 1;i += 2){
            long l = seeds.get(i), r = seeds.get(i) + seeds.get(i + 1) - 1;
            if(getLocFromSeed(l) < getLocFromSeed(r)){
                min = Math.min(getLocFromSeed(l), min);
            } else {
                while(l < r){
                    long mid = l + (r - l) / 2;
                    if(getLocFromSeed(mid) > getLocFromSeed(r)){
                        l = mid;
                    } else if(getLocFromSeed(mid) < getLocFromSeed(r) && getLocFromSeed(mid) < getLocFromSeed(mid - 1)){
                        min = Math.min(getLocFromSeed(mid), min);
                        break;
                    } else {
                        r = mid;
                    }
                }
            }
            
            // for(long seed = seeds.get(i); seed < seeds.get(i) + seeds.get(i + 1);seed++){
            //     long loca = getLocFromSeed(seed);
            //     min = Math.min(loca, min);
            // }
        }
        System.out.println(min);
    }

    static long getLocFromSeed(long seed){
        long soil = check(seed, seed_soil);
        long fert = check(soil, soil_fert);
        long water = check(fert, fert_water);;
        long light = check(water, water_light);;
        long temp = check(light, light_temp);
        long humi = check(temp, temp_humi);
        long loca = check(humi, humi_loca);
        return loca;
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
