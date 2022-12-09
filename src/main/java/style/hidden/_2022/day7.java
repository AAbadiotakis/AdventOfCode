package style.hidden._2022;

import style.hidden.utils.Runner;

import java.util.HashMap;
import java.util.Map;

public class day7 extends Runner {

    public day7(String path) throws Exception {
        super(path);
    }

    public static void main(String[] args) throws Exception {
        Runner runner = new day7("_2022/day7input.txt");
        runner.part1Code();
        runner.part2Code();
    }


    @Override
    public void part1Code() {
        Map<String, String> fileSystem = new HashMap<>();
        String currentDirectory = "/";
        fileSystem.put("/", "dir");
        for (int i = 0; i < getLines().size(); i++) {
            String line = getLines().get(i);
            if (line.startsWith("$ cd")) {
                String cdCommand = line.split("cd")[1].trim();
                if ("/".equals(cdCommand)) {
                    currentDirectory = cdCommand;
                } else if ("..".equals(cdCommand)) {
                    currentDirectory = currentDirectory.substring(0, currentDirectory.lastIndexOf("/"));
                } else {
                    if (currentDirectory.endsWith("/")) {
                        currentDirectory += cdCommand;
                    } else {
                        currentDirectory += "/" + cdCommand;
                    }
                }
            } else if (line.equals("$ ls")) {
                do {
                    i++;
                    if(i < getLines().size()) {
                        line = getLines().get(i);
                    }
                    if(i < getLines().size() && !line.startsWith("$")) {
                        if (line.startsWith("dir")) {
                            if(currentDirectory.endsWith("/")) {
                                fileSystem.put(currentDirectory + line.split(" ")[1], "dir");
                            } else {
                                fileSystem.put(currentDirectory + "/" + line.split(" ")[1], "dir");
                            }
                        } else {
                            if(currentDirectory.endsWith("/")) {
                                fileSystem.put(currentDirectory + line.split(" ")[1], line.split(" ")[0]);
                            } else {
                                fileSystem.put(currentDirectory + "/" + line.split(" ")[1], line.split(" ")[0]);
                            }
                        }
                    }
                } while(!line.startsWith("$") && i < getLines().size());
                i--;
            }
        }
        System.out.println(fileSystem);
        Map<String, Long> directorySizes = new HashMap<>();
        for(String key : fileSystem.keySet()) {
            if(fileSystem.get(key).equals("dir")) {
                directorySizes.put(key, 0L);
            }
        }

        for(String key: fileSystem.keySet()) {
            if(!fileSystem.get(key).equals("dir")) {
                String dir = key;
                while(dir.lastIndexOf("/") >= 0) {
                    if(dir.lastIndexOf("/") == 0) {
                        directorySizes.put("/", directorySizes.get("/") + Long.parseLong(fileSystem.get(key)));
                        dir = "";
                    } else {
                        dir = dir.substring(0, dir.lastIndexOf("/"));
                        directorySizes.put(dir, directorySizes.get(dir) + Long.parseLong(fileSystem.get(key)));
                    }
                }
            }
        }


        long answer = 0;
        for(String key : directorySizes.keySet()) {
            if(directorySizes.get(key) <= 100000) {
                answer += directorySizes.get(key);
            }
        }

        System.out.println("Part 1 Answer = " + answer);
        System.out.println("Main Directory Size = " + directorySizes.get("/"));
        long totalDiskSpaceAvailable = 70000000 - directorySizes.get("/");
        System.out.println("Total Disk Space Available = " + totalDiskSpaceAvailable);
        long diskSpaceNeededToFree = 30000000 - totalDiskSpaceAvailable;
        System.out.println("Disk Space Needed to Free = " + diskSpaceNeededToFree);

        long closestValueToDiskSpaceNeededToFree = Long.MAX_VALUE;
        for(String key : directorySizes.keySet()) {
            if(diskSpaceNeededToFree < directorySizes.get(key)) {
                if(closestValueToDiskSpaceNeededToFree > directorySizes.get(key)) {
                    closestValueToDiskSpaceNeededToFree = directorySizes.get(key);
                }
            }
        }
        System.out.println("Answer Part 2 = " + closestValueToDiskSpaceNeededToFree);

    }

    @Override
    public void part2Code() {
    }

}
