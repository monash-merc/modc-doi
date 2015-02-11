package edu.monash.merc.eddy.modc.repository;

import edu.monash.merc.eddy.modc.domain.Avatar;

/**
 * Created by simonyu on 8/08/2014.
 */
public interface AvatarRepository {

    Avatar getAvatarByUserId(long userId);
}
