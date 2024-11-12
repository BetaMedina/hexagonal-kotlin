package com.hexagonal.core.port.`in`

import com.hexagonal.core.enums.ClientRolesEnum

interface AdminPort {
    fun changeRoles(rolesEnum: ClientRolesEnum,id:Int)
}