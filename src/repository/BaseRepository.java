package repository;

import constants.GeneralConstant;
import model.BaseEntity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepository<T extends BaseEntity> implements GenericRepository<T> {
    public final String fileName;

    public BaseRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                boolean isEndOfFile = false;
                while (!isEndOfFile) {
                    try {
                        T entity = (T) in.readObject();
                        list.add(entity);
                    } catch (EOFException e) {
                        isEndOfFile = true;
                    }
                }
            } catch (Exception e) {
                System.out.println(GeneralConstant.FILE_OPERATION_ERROR);
            }
        }
        return list;
    }

    @Override
    public void saveAll(List<T> list) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (T entity : list) {
                out.writeObject(entity);
            }
        } catch (IOException e) {
            System.out.println(GeneralConstant.FILE_WRITE_ERROR);
        }
    }

    @Override
    public void delete(int id) {
        List<T> list = getAll();
        boolean found = list.removeIf(entity -> entity.getId() == id);
        if (found) {
            saveAll(list);
            System.out.println(GeneralConstant.ITEM_DELETED_SUCCESSFULLY);
        } else {
            System.out.println(GeneralConstant.NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public void update(T updated) {
        List<T> list = getAll();
        boolean found = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == updated.getId()) {
                list.set(i, updated);
                found = true;
                break;
            }
        }

        if (found) {
            saveAll(list);
            System.out.println(GeneralConstant.UPDATE_OPERATION_SUCCESSFUL);
        } else {
            System.out.println(GeneralConstant.NOT_FOUND_EXCEPTION);
        }
    }
}