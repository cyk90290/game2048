package com.example.myapplication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
public class GameSave {
    public boolean saveInfo(int[][] array) {
        try {
            OutputStream os = new FileOutputStream("");
            os.write(array.length);
            os.write(array[0].length);
            for (int r = 0; r < array.length; r++) {
                for (int c = 0; c < array.length; c++) {
                    os.write(array[r][c]);
                }
            }
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int[][] openInfo() {
        try {
            InputStream is = new FileInputStream("");
            int row = is.read();
            int column = is.read();
            if (row != -1 && column != -1) {
                int[][] array = new int[row][column];

                for (int r = 0; r < array.length; r++) {
                    for (int c = 0; c < array.length; c++) {
                        array[r][c] = is.read();
                    }
                }
                is.close();
                return array;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean saveRankingList(User user) {
        try {
            ArrayList<User> list = openRankingList();
            if (list != null) {
                list.add(user);
                for (int i = 0; i < list.size() - 1; i++) {
                    int index = i;
                    for (int j = i + 1; j < list.size(); j++) {
                        User use_max = (User) list.get(index);
                        User use = (User) list.get(j);
                        if (use_max.getMax() <= use.getMax()) {
                            index = j;
                        }
                    }
                    if (index != i) {
                        User use_max = (User) list.get(index);
                        User use = (User) list.get(i);
                        list.set(index, use);
                        list.set(i, use_max);
                    }
                }
            } else {
                list = new ArrayList<User>();
                list.add(user);
            }
            OutputStream os = new FileOutputStream("");
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(list.size());
            for (int i = 0; i < list.size(); i++) {
                User use = (User) list.get(i);
                dos.writeByte(use.getName().getBytes().length);
                dos.write(use.getName().getBytes());
                dos.writeInt(use.getMax());
            }
            dos.close();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<User> openRankingList() {
        try {
            InputStream is = new FileInputStream("");
            DataInputStream d = new DataInputStream(is);
            int size = d.readInt();
            ArrayList<User> list = new ArrayList<User>();
            if (size != -1) {
                for (int i = 0; i < size; i++) {
                    byte nSize = d.readByte();
                    byte[] b = new byte[nSize];
                    is.read(b);
                    int max = d.readInt();
                    User use = new User(new String(b), max);
                    list.add(use);
                }
            }
            d.close();
            is.close();
            return list;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
