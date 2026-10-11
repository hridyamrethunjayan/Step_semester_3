interface Reservable {
    void reserve(String member);
}

interface Downloadable {
    void download();
}

class Resource {
    String id;

    Resource(String id) {
        this.id = id;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id);
    }

    public void reserve(String member) {
        System.out.println(id + " reserved for " + member);
    }
}

class EBook extends Resource implements Downloadable {
    EBook(String id) {
        super(id);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class LibraryPlatform {
    public static void main(String[] args) {
        Resource[] resources = new Resource[100];
        int size = 0;

        Resource[] added = {
            new Book("B1"),
            new Book("B1"),
            new EBook("E1")
        };

        for (Resource r : added) {
            int pos = 0;

            while (pos < size &&
                    resources[pos].id.compareTo(r.id) < 0)
                pos++;

            if (pos < size && resources[pos].id.equals(r.id)) {
                System.out.println("duplicate rejected");
                continue;
            }

            for (int i = size; i > pos; i--)
                resources[i] = resources[i - 1];

            resources[pos] = r;
            size++;
            System.out.println(r.id + " added");
        }

        for (int i = 0; i < size; i++) {
            if (resources[i].id.equals("B1")) {
                if (resources[i] instanceof Reservable)
                    ((Reservable) resources[i]).reserve("M1");
                else
                    System.out.println("B1 rejected: reserve unsupported");

                if (resources[i] instanceof Downloadable)
                    ((Downloadable) resources[i]).download();
                else
                    System.out.println("B1 rejected: download unsupported");
            }
        }

        for (int i = 0; i < size; i++) {
            if (resources[i].id.equals("E1")) {
                if (resources[i] instanceof Downloadable)
                    ((Downloadable) resources[i]).download();
                else
                    System.out.println("E1 rejected: download unsupported");
            }
        }

        int left = 0, right = size - 1, mid, found = -1;

        while (left <= right) {
            mid = left + (right - left) / 2;

            if (resources[mid].id.equals("E1")) {
                found = mid;
                break;
            } else if (resources[mid].id.compareTo("E1") < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (found != -1)
            System.out.println("E1 found at index " + found);
        else
            System.out.println("E1 not found");
    }
}