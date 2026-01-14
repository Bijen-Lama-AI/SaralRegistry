package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Manages citizen registration. Uses a HashMap for fast lookup and prevent
 * duplication or invalid entries.
 *
 * @author bijen
 */
public class CitizenRegistryModel {

    // Hashmap for quick access by citizenship number
    private LinkedHashMap<String, CitizenModel> citizenMap;

    public CitizenRegistryModel() {
        this.citizenMap = new LinkedHashMap<>();
    }

    /**
     * Adds a citizen to the registry. Prevent null entries and duplicate
     * citizenship number.
     *
     * @param citizen Citizen to add
     * @throws IllegalArgumentException if citizen is null or already exists
     */
    public void addCitizen(CitizenModel citizen) {

        if (citizen == null) {
            throw new IllegalArgumentException("Citizen cannot be null.");
        }

        String citizenshipNumber = citizen.getCitizenshipNumber();
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Citizen must have valid citizenship number.");
        }

        String key = citizenshipNumber.trim();

        if (citizenMap.containsKey(key)) {
            throw new IllegalArgumentException("Citizen with this citizenship number already exist.");
        }

        citizenMap.put(key, citizen);
    }

    /**
     * Returns a list of all citizens data.
     *
     * @return ArrayList of citizens
     */
    public List<CitizenModel> getAllCitizens() {
        return new ArrayList<>(citizenMap.values());
    }

    /**
     * Finds a citizen by citizenship number. Return null if not found or if
     * input is invalid.
     *
     * @param citizenshipNumber Citizenship number to search
     * @return CitizenModel or null
     */
    public CitizenModel findByCitizenshipNumber(String citizenshipNumber) {

        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            return null;
        }

        return citizenMap.get(citizenshipNumber.trim());
    }

    /**
     * Updates the registration status of a citizen. Useful for admin actions
     * like approve/reject.
     *
     * @param citizenshipNumber
     * @param status
     */
    public void updateCitizenStatus(String citizenshipNumber, CitizenModel.RegistrationStatus status) {
        CitizenModel citizen = findByCitizenshipNumber(citizenshipNumber);
        if (citizen == null) {
            throw new IllegalArgumentException("Citizen not found.");
        }
        citizen.setStatus(status);
    }

    /**
     * updates
     *
     * @param citizen
     */
    public void updateCitizen(CitizenModel citizen) {
        if (citizen == null) {
            throw new IllegalArgumentException("Citizen cannot be null");
        }

        String key = sampleKey(citizen.getCitizenshipNumber());

        if (!citizenMap.containsKey(key)) {
            throw new IllegalArgumentException("Citizen not found");
        }
        citizenMap.put(key, citizen);
    }

    public boolean removeCitizen(String citizenshipNumber) {
        if (citizenshipNumber == null || citizenshipNumber.trim().isEmpty()) {
            return false;
        }
        return citizenMap.remove(sampleKey(citizenshipNumber)) != null;
    }

    public List<CitizenModel> findByName(String name) {
        List<CitizenModel> results = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) {
            return results;
        }
        String searchName = name.trim().toLowerCase();
        for (CitizenModel citizen : citizenMap.values()) {
            if (citizen.getVoterName().toLowerCase().contains(searchName)) {
                results.add(citizen);
            }
        }
        return results;
    }

    public boolean updateCitizenshipNumber(String oldCid, String newCid, CitizenModel citizen) {
        if (oldCid == null || newCid == null || citizen == null) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        String oldKey = oldCid.trim();
        String newKey = newCid.trim();

        if (!citizenMap.containsKey(oldKey)) {
            throw new IllegalArgumentException("Original citizen not found");
        }

        if (citizenMap.containsKey(newKey) && !oldKey.equals(newKey)) {
            throw new IllegalArgumentException("New citizenship number already exists");
        }

        // Remove old entry
        citizenMap.remove(oldKey);

        // Update citizen's citizenship number
        citizen.setCitizenshipNumber(newCid);

        // Add with new key
        citizenMap.put(newKey, citizen);

        return true;
    }

    public int count() {
        return citizenMap.size();
    }

    private String sampleKey(String citizenshipNumber) {
        return citizenshipNumber.trim().toUpperCase();
    }
}
