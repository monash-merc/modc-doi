package edu.monash.merc.eddy.modc.repository;

import edu.monash.merc.eddy.modc.domain.Profile;

/**
 * Created by simonyu on 8/08/2014.
 */
public interface ProfileRepository {

    Profile getProfileByUserId(long userId);
}
